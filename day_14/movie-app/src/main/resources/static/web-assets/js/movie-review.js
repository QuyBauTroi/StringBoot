const stars = document.querySelectorAll(".star");
const ratingValue = document.getElementById("rating-value");

let currentRating = 0;

stars.forEach((star) => {
    star.addEventListener("mouseover", () => {
        resetStars();
        const rating = parseInt(star.getAttribute("data-rating"));
        highlightStars(rating);
    });

    star.addEventListener("mouseout", () => {
        resetStars();
        highlightStars(currentRating);
    });

    star.addEventListener("click", () => {
        currentRating = parseInt(star.getAttribute("data-rating"));
        ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);
    });
});

function resetStars() {
    stars.forEach((star) => {
        star.classList.remove("active");
    });
}

function highlightStars(rating) {
    stars.forEach((star) => {
        const starRating = parseInt(star.getAttribute("data-rating"));
        if (starRating <= rating) {
            star.classList.add("active");
        }
    });
}

// render review
const formatDate = dateStr => {
    const date = new Date(dateStr);
    const day = `0${date.getDate()}`.slice(-2); // 01 -> 01, 015 -> 15
    const month = `0${date.getMonth() + 1}`.slice(-2);
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}
const reviewListEl = document.querySelector(".review-list");
const renderReview = (reviews) => {
    let html = "";
    reviews.forEach((review, index) => {
        html += `
            <div class="rating-item d-flex align-items-center mb-3 pb-3" data-index="${index}">
                <div class="rating-avatar">
                    <img src="${review.user.avatar}" alt="${review.user.name}">
                </div>
                <div class="rating-info ms-3">
                    <div class="d-flex align-items-center">
                        <p class="rating-name mb-0">
                            <strong>${review.user.name}</strong>
                        </p>
                        <p class="rating-time mb-0 ms-2">${formatDate(review.createdAt)}</p>
                    </div>
                    <div class="rating-star">
                        <p class="mb-0 fw-bold">
                            <span class="rating-icon"><i class="fa fa-star"></i></span>
                            <span>${review.rating}/10 Tuyệt vời</span>
                        </p>
                    </div>
                    <p class="rating-content mt-1 mb-0 text-muted">${review.content}</p>
                    <button class="btn btn-primary btn-edit" data-index="${index}">Sửa</button>
                    <button class="btn btn-danger btn-delete" data-index="${index}">Xóa</button>
                </div>
            </div>
        `;
    });

    reviewListEl.innerHTML = html;
}

// Tạo review
const formReviewEl = document.getElementById("form-review");
const reviewContentEl = document.getElementById("review-content");
const modalReviewEl = document.getElementById("modal-review");
const myModalReviewEl = new bootstrap.Modal(modalReviewEl, {
    keyboard: false
})

modalReviewEl.addEventListener('hidden.bs.modal', event => {
    console.log("Su kien dong modal")
    currentRating = 0;
    reviewContentEl.value = "";
    ratingValue.textContent = "";
    resetStars();
})

formReviewEl.addEventListener("submit", async (e) => {
    e.preventDefault();

    if (currentRating === 0) {
        alert("Vui lòng chọn số sao");
        return;
    }

    if (reviewContentEl.value.trim() === "") {
        alert("Nội dung đánh giá không được để trống");
        return;
    }

    const data = {
        content: reviewContentEl.value,
        rating: currentRating,
        movieId: movie.id
    };

    const index = formReviewEl.getAttribute("data-index");

    try {
        if (index !== null) {
            let res = await axios.put(`/api/reviews/${reviews[index].id}`, data);
            reviews[index] = res.data;
            formReviewEl.removeAttribute("data-index");
        } else {
            let res = await axios.post("/api/reviews", data);
            reviews.unshift(res.data);
        }

        renderReview(reviews);
        myModalReviewEl.hide();

        currentRating = 0;
        reviewContentEl.value = "";
        ratingValue.textContent = "";
        resetStars();
    } catch (e) {
        console.log(e);
    }
});

reviewListEl.addEventListener("click", (e) => {
    if (e.target.classList.contains("btn-edit")) {
        const index = e.target.getAttribute("data-index");
        const review = reviews[index];

        currentRating = review.rating;
        reviewContentEl.value = review.content;
        ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);

        myModalReviewEl.show();

        formReviewEl.setAttribute("data-index", index);
    }

    if (e.target.classList.contains("btn-delete")) {
        const index = e.target.getAttribute("data-index");
        deleteReview(index);
    }
});

const deleteReview = async (index) => {
    try {
        await axios.delete(`/api/reviews/${reviews[index].id}`);
        reviews.splice(index, 1);
        renderReview(reviews);
    } catch (e) {
        console.log(e);
    }
};
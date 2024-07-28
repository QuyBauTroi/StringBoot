const stars = document.querySelectorAll(".star");
const ratingValue = document.getElementById("rating-value");

let currentRating = 0;
let currentReviewId = null; // Thêm biến này để lưu review hiện tại khi sửa

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

//render
const formatDate = dateStr => {
    const date = new Date(dateStr);
    const day = `0${date.getDate()}`.slice(-2);
    const month = `0${date.getMonth() + 1}`.slice(-2);
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
};

const reviewListEl = document.querySelector(".review-list");

const renderReview = reviews => {
    let html = "";
    reviews.forEach(review => {
        html += `
            <div class="rating-item d-flex align-items-center mb-3 pb-3" data-review-id="${review.id}">
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
                    ${
            currentUser != null && currentUser.id === review.user.id
                ? `
                                <div>
                                    <button class="border-0 bg-transparent btn-edit-review text-primary me-2 text-decoration-underline">
                                        Sửa
                                    </button>
                                    <button class="border-0 bg-transparent btn-delete-review text-danger text-decoration-underline">
                                        Xóa
                                    </button>
                                </div>
                                `
                : ''
        }
                </div>
            </div>
        `;
    });

    reviewListEl.innerHTML = html;

    // Gán sự kiện cho các nút sửa và xóa sau khi render
    document.querySelectorAll(".btn-edit-review").forEach(btn => {
        btn.addEventListener("click", handleEditReview);
    });

    document.querySelectorAll(".btn-delete-review").forEach(btn => {
        btn.addEventListener("click", handleDeleteReview);
    });
};

// Xử lý sự kiện sửa review
const handleEditReview = (event) => {
    const reviewItem = event.target.closest(".rating-item");
    const reviewId = reviewItem.getAttribute("data-review-id");
    const review = reviews.find(r => r.id === parseInt(reviewId));

    if (review) {
        currentReviewId = review.id;
        reviewContentEl.value = review.content;
        currentRating = review.rating;
        ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);
        myModalReviewEl.show();
    }
};



const handleDeleteReview = async (event) => {
    const reviewItem = event.target.closest(".rating-item");
    const reviewId = reviewItem.getAttribute("data-review-id");

    if (confirm("Bạn có chắc chắn muốn xóa đánh giá này?")) {
        try {
            // Gọi API để xóa bình luận
            const response = await axios.delete(`/api/reviews/${reviewId}`);

            // Kiểm tra kết quả trả về từ server
            if (response.status === 204) {
                // Xóa review khỏi danh sách reviews local
                for (let i = 0; i < reviews.length; i++) {
                    if (reviews[i].id === parseInt(reviewId)) {
                        reviews.splice(i, 1); // Xóa phần tử tại vị trí i
                        break; // Thoát khỏi vòng lặp sau khi xóa
                    }
                }

                // Render lại danh sách reviews
                renderReview(reviews);

                // Hiển thị thông báo thành công
                toastr.success("Đánh giá đã được xóa thành công");
            } else {
                // Xử lý trường hợp lỗi từ server
                throw new Error("Không thể xóa đánh giá. Vui lòng thử lại sau.");
            }
        } catch (error) {
            console.error("Lỗi khi xóa đánh giá:", error);
            toastr.error("Có lỗi xảy ra khi xóa đánh giá");
        }
    }
};







// Tạo hoặc cập nhật review
const formReviewEl = document.getElementById("form-review");
const reviewContentEl = document.getElementById("review-content");
const modalReviewEl = document.getElementById("modal-review");
const myModalReviewEl = new bootstrap.Modal(modalReviewEl, {
    keyboard: false
});

modalReviewEl.addEventListener('hidden.bs.modal', event => {
    currentReviewId = null;
    currentRating = 0;
    reviewContentEl.value = "";
    ratingValue.textContent = "";
    resetStars();
});

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

    try {
        if (currentReviewId) {
            await axios.put(`/api/reviews/${currentReviewId}`, data);
            const index = reviews.findIndex(r => r.id === currentReviewId);
            reviews[index] = { ...reviews[index], ...data };
            toastr.success("Bình luận của bạn đã được chỉnh sửa");
        } else {
            let res = await axios.post("/api/reviews", data);
            reviews.unshift(res.data);
            toastr.success("Đánh giá thành công");
        }

        renderReview(reviews);
        myModalReviewEl.hide();
    } catch (e) {
        console.log(e);
        toastr.error("Có lỗi xảy ra khi lưu đánh giá");
    }
});



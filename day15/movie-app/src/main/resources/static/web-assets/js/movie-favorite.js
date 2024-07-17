document.addEventListener('DOMContentLoaded', async () => {
    const addToFavoriteBtn = document.getElementById("add-to-favorite");
    const removeFromFavoriteBtn = document.getElementById("remove-from-favorite");

    // Hàm thêm phim vào danh sách yêu thích
    const addToFavorite = async () => {
        if (!currentUser) {
            toastr.error("Vui lòng đăng nhập để thực hiện chức năng này.");
            return;
        }
        const data = {
            userId: currentUser.id,
            movieId: movie.id,
        };
        try {
            const response = await axios.post("/api/favorites", data);
            if (response.status === 201) {
                toastr.success("Thêm vào danh sách yêu thích thành công");
                toggleButtons(true);
            }
        } catch (error) {
            console.log(error);
            toastr.error(error.response.data.message);
        }
    };

    // Hàm xóa phim khỏi danh sách yêu thích
    const removeFromFavorite = async () => {
        try {
            const response = await axios.delete(`/api/favorites/${movie.id}`);
            if (response.status === 204) {
                toastr.success("Xóa khỏi danh sách yêu thích thành công");
                toggleButtons(false);
            }
        } catch (error) {
            console.log(error);
            toastr.error(error.response.data.message);
        }
    };

    // Hàm để chuyển đổi giữa nút thêm và nút xóa
    const toggleButtons = (isFavorite) => {
        if (isFavorite) {
            addToFavoriteBtn.style.display = "none";
            removeFromFavoriteBtn.style.display = "block";
        } else {
            addToFavoriteBtn.style.display = "block";
            removeFromFavoriteBtn.style.display = "none";
        }
    };

    // Hàm kiểm tra xem phim hiện tại có trong danh sách yêu thích của người dùng hay không
    const checkFavoriteStatus = async () => {
        try {
            const response = await axios.get(`/api/favorites/check/${currentUser.id}/${movie.id}`);
            toggleButtons(response.data.isFavorite);
        } catch (error) {
            console.log(error);
        }
    };

    // Đăng ký sự kiện click cho nút thêm và nút xóa
    addToFavoriteBtn.addEventListener('click', addToFavorite);
    removeFromFavoriteBtn.addEventListener('click', removeFromFavorite);

    // Kiểm tra trạng thái yêu thích khi trang được tải
    await checkFavoriteStatus();
});
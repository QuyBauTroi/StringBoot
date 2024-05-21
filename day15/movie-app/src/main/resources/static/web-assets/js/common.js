// Xử lý active menu
function activeMenu() {
    // Lây đường dẫn hiện tại
    let path = window.location.pathname;
    console.log(path)

    // Xử lý active menu
    const menuItems = document.querySelectorAll('#main-menu .navbar-nav .nav-link')
    menuItems.forEach(function (menu) {
        if (menu.getAttribute('href') === path) {
            menu.classList.add('active')
        }
    })
}
activeMenu()

// xu ly log out

document.getElementById("logout").addEventListener("click", function (event) {
    event.preventDefault();

    fetch('/api/auth/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'same-origin'
    })
        .then(response => {
            if (response.ok) {
                toastr.success('Đăng xuất thành công');
                setTimeout(() => {
                    window.location.href = '/dang-nhap';
                }, 1000);
            } else {
                // Handle errors
                response.json().then(data => {
                    toastr.error('Đăng xuất thất bại: ' + data.message, 'Error');
                }).catch(() => {
                    toastr.error('Đăng xuất thất bại', 'Error');
                });
            }
        })
        .catch(error => {
            console.error("Error:", error);
            toastr.error('Đăng xuất thất bại', 'Error');
        });
})

toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-right",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}
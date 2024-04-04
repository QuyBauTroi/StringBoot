const dataList = document.getElementById('dataList');
        const postsBtn = document.getElementById('postsBtn');
        const albumsBtn = document.getElementById('albumsBtn');
        const photosBtn = document.getElementById('photosBtn');

        // Mặc định gọi API posts khi trang được load
        fetchData('posts');

        function fetchData(resource) {
            // Xóa hiệu ứng active của tất cả các button
            postsBtn.classList.remove('active');
            albumsBtn.classList.remove('active');
            photosBtn.classList.remove('active');

            // Highlight button tương ứng với resource được chọn
            if (resource === 'posts') {
                postsBtn.classList.add('active');
            } else if (resource === 'albums') {
                albumsBtn.classList.add('active');
            } else if (resource === 'photos') {
                photosBtn.classList.add('active');
            }

            // Gọi API tương ứng với resource và hiển thị dữ liệu
            fetch(`https://jsonplaceholder.typicode.com/${resource}`)
                .then(response => response.json())
                .then(data => {
                    dataList.innerHTML = '';
                    data.forEach(item => {
                        const li = document.createElement('li');
                        li.textContent = item.title;
                        dataList.appendChild(li);
                    });
                })
                .catch(error => console.error('Error fetching data:', error));
        }
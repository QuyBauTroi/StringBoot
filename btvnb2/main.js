// Danh sách các sản phẩm có trong giỏ hàng
let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]
// 1. In ra thông tin các sản phẩm trong giỏ hàng
console.log("Thông tin các sản phẩm trong giỏ hàng:");
products.forEach(product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});

// 2. Tính tổng tiền tất cả sản phẩm trong giỏ hàng
let totalCost = products.reduce((acc, product) => acc + (product.price * product.count), 0);
console.log(`Tổng tiền tất cả sản phẩm trong giỏ hàng: ${totalCost}`);

// 3. Tìm các sản phẩm của thương hiệu "Apple"
let appleProducts = products.filter(product => product.brand === "Apple");
console.log("Các sản phẩm của thương hiệu Apple:", appleProducts);

// 4. Tìm các sản phẩm có giá > 20000000
let expensiveProducts = products.filter(product => product.price > 20000000);
console.log("Các sản phẩm có giá trên 20 triệu:", expensiveProducts);

// 5. Tìm các sản phẩm có chữ "pro" trong tên (Không phân biệt hoa thường)
let proProducts = products.filter(product => product.name.toLowerCase().includes("pro"));
console.log("Các sản phẩm có chữ 'pro' trong tên:", proProducts);

// 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product
products.push({
    name: "Google Pixel 6",
    price: 7000000,
    brand: "Google",
    count: 2,
});

// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng
products = products.filter(product => product.brand !== "Samsung");

// 8. Sắp xếp giỏ hàng theo price tăng dần
products.sort((a, b) => a.price - b.price);

// 9. Sắp xếp giỏ hàng theo count giảm dần
products.sort((a, b) => b.count - a.count);

// 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng
let randomProducts = products.slice(0, 2);

console.log("Kết quả sau khi thực hiện các yêu cầu:");
console.log(products);
console.log("2 sản phẩm ngẫu nhiên trong giỏ hàng:", randomProducts);
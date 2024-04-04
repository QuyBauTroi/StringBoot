function filterUsersByAgeAndStatus(users) {
    return users.filter(user => user.age > 25 && user.isStatus);
}
function sortUsersByAge(users) {
    return users.sort((a, b) => a.age - b.age);
}
// Ví dụ sử dụng
const users = [
    {
        name: "Bùi Công Sơn",
        age: 30,
        isStatus: true
    },
    {
        name: "Nguyễn Thu Hằng",
        age: 27,
        isStatus: false
    },
    {
        name: "Phạm Văn Dũng",
        age: 20,
        isStatus: false
    }
];

console.log(filterUsersByAgeAndStatus(users));
console.log(sortUsersByAge(users));
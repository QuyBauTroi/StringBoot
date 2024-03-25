const text = document.getElementById("text")
text.style.color= "#777";
text.style.fontSize = "2rem";
text.innerHTML = "Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript.";



var listItems = document.querySelectorAll("ul li");

    for (var i = 0; i < listItems.length; i++) {
        listItems[i].style.color = "blue";
    }


// Thêm 3 thẻ <li> mới vào cuối danh sách
for (var i = 8; i <= 10; i++) {
    var newItem = document.createElement("li");
    newItem.textContent = "Item " + i;
    document.getElementById("list").appendChild(newItem);
}

// Sửa nội dung cho thẻ <li> 1 thành màu đỏ
document.getElementById("list").getElementsByTagName("li")[0].style.color = "red";

// Sửa background cho thẻ <li> 3 thành màu xanh
document.getElementById("list").getElementsByTagName("li")[2].style.backgroundColor = "green";

// Remove thẻ <li> 4
var list = document.getElementById("list");
var listItemToRemove = list.getElementsByTagName("li")[3];
list.removeChild(listItemToRemove);

// Thêm thẻ <li> mới thay thế cho thẻ <li> 4 bị xóa ở bước trước
var newListItem = document.createElement("li");
newListItem.textContent = "New Item";
list.insertBefore(newListItem, list.childNodes[3]);    
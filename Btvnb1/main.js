// b1
function calculateFactorial(n) {
    if (n < 0) {
        return "Không thể tính giai thừa cho số âm";
    } else if (n === 0) {
        return 1;
    } else {
        return n * calculateFactorial(n - 1);
    }
}
console.log(calculateFactorial(5));

//b2
function reverseString(str) {
    return str.split('').reverse().join('');
}
console.log(reverseString('hello'));

//b3
function translate(countryCode) {
    switch (countryCode) {
        case 'VN':
            return 'Xin chào';
        case 'EN':
            return 'Hello';
        default:
            return 'Mã quốc gia không hợp lệ';
    }
}
console.log(translate('VN'));
console.log(translate('EN')); 
console.log(translate('US')); 


//b4
function subString(str) {
    if (str.length > 15) {
        return str.substring(0, 10) + '...';
    } else {
        return str;
    }
}
console.log(subString("xinchaocacbandenvoiTechmaster"));
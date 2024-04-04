function getStringHasMaxLength(strings) {
    let maxLength = 0;
    for (let str of strings) {
        if (str.length > maxLength) {
            maxLength = str.length;
        }
    }

    let result = [];    for (let str of strings) {
        if (str.length === maxLength) {
            result.push(str);
        }
    }

    return result;
}

console.log(getStringHasMaxLength(['aba', 'aa', 'ad', 'c', 'vcd']));
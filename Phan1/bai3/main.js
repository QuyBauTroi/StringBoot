function getCountElement(arr) {
    let counts = {};
    arr.forEach(element => {
        counts[element] = (counts[element] || 0) + 1;
    });
    
    return counts;
}


const result = getCountElement(["one", "two", "three", "one", "one", "three"]);
console.log(result);


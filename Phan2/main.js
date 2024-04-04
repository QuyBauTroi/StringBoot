const quizes = [
    {
        id: 1,
        question: "1 + 1 = ?",
        answers: [1, 2, 3, 4],
    },
    {
        id: 2,
        question: "2 + 2 = ?",
        answers: [2, 3, 4, 5],
    },
    {
        id: 3,
        question: "3 + 3 = ?",
        answers: [3, 4, 5, 6],
    },
];
document.getElementById('btn').addEventListener('click', randomAnswers);

function randomAnswers() {
    quizes.forEach(quiz => {
        const randomIndex = Math.floor(Math.random() * quiz.answers.length);
        const radioInputs = document.querySelectorAll(`input[name="${quiz.id}"]`);
        radioInputs[randomIndex].checked = true;
    });
}
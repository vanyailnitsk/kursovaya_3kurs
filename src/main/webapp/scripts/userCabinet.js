function openCreateExpenseModal() {
    document.getElementById('createExpenseModal').style.display = 'block';
    console.log("open")
}

function closeCreateExpenseModal() {
    document.getElementById('createExpenseModal').style.display = 'none';
}

window.onclick = function(event) {
    var modal = document.getElementById('createExpenseModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
};
function submitExpenseForm(event) {
    event.preventDefault(); // Предотвратить отправку формы (браузерная перезагрузка)

    var form = document.getElementById('createExpenseForm');

    // Получить данные из формы
    var formData = new FormData(form);
    console.log(formData)

    // fetch('/your-api-endpoint', {
    //     method: 'POST',
    //     body: formData
    // })
    // .then(response => response.json())
    // .then(data => {
    //     console.log('Success:', data);
    // })
    // .catch(error => {
    //     console.error('Error:', error);
    // });

    closeCreateExpenseModal();
}
document.getElementById('createExpenseForm').addEventListener('submit', submitExpenseForm);

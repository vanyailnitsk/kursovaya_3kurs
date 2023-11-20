function openCreateExpenseModal() {
    document.getElementById('createExpenseModal').style.display = 'block';
}

function closeCreateExpenseModal() {
    document.getElementById('createExpenseModal').style.display = 'none';
    document.getElementById('createExpenseForm').reset();
}

window.onclick = function (event) {
    var modal = document.getElementById('createExpenseModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
};

function submitExpenseForm(event) {
    event.preventDefault(); // Предотвратить отправку формы (браузерная перезагрузка)

    var form = document.getElementById('createExpenseForm');

    // var formData = new FormData(form);
    var formData = {
        userId: document.getElementById('user_id').value,
        amount: document.getElementById('amount').value,
        source: document.getElementById('source').value,
        categoryId: document.getElementById('category_id').value
    };
    console.log(formData)

    fetch('/expense', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(error => {
                    throw new Error(error.error);
                });
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('message').innerHTML = 'Запись успешно добавлена! '
        })
        .catch(error => {
            console.error('Error:', error.message);
            console.log('Server Error:', error.message);
            document.getElementById('message').innerHTML = 'Произошла ошибка! ' +error.message
        });

}

document.getElementById('createExpenseForm').addEventListener('submit', submitExpenseForm);

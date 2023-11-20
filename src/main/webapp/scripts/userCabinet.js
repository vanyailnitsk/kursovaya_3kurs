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
        document.getElementById('createExpenseForm').reset();
    }
};

function submitExpenseForm(event) {
    event.preventDefault();

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
            setTimeout(() => location.reload(),1000)
        })
        .catch(error => {
            console.error('Error:', error.message);
            console.log('Server Error:', error.message);
            document.getElementById('message').innerHTML = 'Произошла ошибка! ' +error.message
        });

}
    // Найти все кнопки "Удалить" по классу
    var deleteButtons = document.querySelectorAll('.delete-button');


function deleteExpense(expenseId) {
    console.log(expenseId)

    fetch(`/expense?id=${expenseId}`, {
        method: 'DELETE',
    })
        .then(response => response.text())
        .then(data => {
            location.reload()
        })
        .catch(error => {
            console.error('Error:', error);
        })
}

document.getElementById('createExpenseForm').addEventListener('submit', submitExpenseForm);

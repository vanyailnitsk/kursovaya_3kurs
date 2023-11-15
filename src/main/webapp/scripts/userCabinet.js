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
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
    })
    .catch(error => {
        console.error('Error:', error);
    });

    closeCreateExpenseModal();
}
document.getElementById('createExpenseForm').addEventListener('submit', submitExpenseForm);

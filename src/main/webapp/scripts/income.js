function openCreateIncomeModal() {
  document.getElementById('createIncomeModal').style.display = 'block';
}

function closeCreateIncomeModal() {
  document.getElementById('createIncomeModal').style.display = 'none';
  document.getElementById('createIncomeForm').reset();
}

window.onclick = function (event) {
  let createModal = document.getElementById('createIncomeModal');
  let editModal = document.getElementById('editIncomeModal');
  if (event.target === createModal) {
    createModal.style.display = 'none';
    document.getElementById('createIncomeForm').reset();
  }
  else if (event.target === editModal) {
    editModal.style.display = 'none';
    document.getElementById('editIncomeForm').reset();
  }
};


function submitIncomeForm(event) {
  event.preventDefault();
  let form = document.getElementById('createIncomeForm');
  let formData = {
    userId: document.getElementById('user_id').value,
    amount: document.getElementById('amount').value,
    source: document.getElementById('source').value,
    categoryId: document.getElementById('category_id').value
  };
  console.log(formData)

  fetch('/income', {
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


function deleteIncome(incomeId) {
  console.log(incomeId)

  fetch(`/income?id=${incomeId}`, {
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

document.getElementById('createIncomeForm').addEventListener('submit', submitIncomeForm);

function openEditIncomeModal(IncomeId,amount,source) {
  document.getElementById("edit-income-id").value = IncomeId;
  document.getElementById("edit-amount").value = amount;
  document.getElementById("edit-source").value = source;
  document.getElementById('editIncomeModal').style.display = 'block';
}

function closeEditIncomeModal() {
  document.getElementById('editIncomeModal').style.display = 'none';
  document.getElementById('editIncomeForm').reset();
}

function submitEditIncomeForm(event) {
  event.preventDefault();

  let form = document.getElementById('createIncomeForm');
  let formData = {
    id:document.getElementById("edit-income-id").value,
    amount: document.getElementById('edit-amount').value,
    source: document.getElementById('edit-source').value,
    categoryId: document.getElementById('edit-category-id').value
  };
  console.log(formData)

  fetch('/income', {
    method: 'PUT',
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
        document.getElementById('edit-message').innerHTML = 'Изменения сохранены! '
        setTimeout(() => location.reload(),1000)
      })
      .catch(error => {
        console.error('Error:', error.message);
        console.log('Server Error:', error.message);
        document.getElementById('edit-message').innerHTML = 'Произошла ошибка! ' +error.message
      });
}
document.getElementById('editIncomeForm').addEventListener('submit', submitEditIncomeForm);


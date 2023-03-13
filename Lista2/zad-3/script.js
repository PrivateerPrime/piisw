if (localStorage.getItem('id') == null) {
    localStorage.setItem('id', '0');
}

let isEdit = false;
let editId;

function clearForm() {
    document.getElementById('form').reset();
    isEdit = false;
    editId = undefined;
}

function submitForm() {
    const name = document.getElementById('nameInput').value;
    const content = document.getElementById('contentInput').value;

    console.log(name, content)
    let id;
    if (editId !== undefined)
        id = editId;
    else
        id = localStorage.getItem('id');
    localStorage.setItem('name'+id, name);
    localStorage.setItem('content'+id, content);
    if (editId === undefined) {
        localStorage.setItem('id', (parseInt(id)+1).toString());
        const ul = document.getElementById('list');
        const li = document.createElement('li');
        li.innerText = name;
        ul.appendChild(li);
    }
}

const id = localStorage.getItem('id');
const id_number = parseInt(id);

const ul = document.getElementById('list');

for (let i = 0; i < id_number; i++) {
    const li = document.createElement('li');
    li.innerText = localStorage.getItem('name' + i);
    li.setAttribute('id', i.toString())
    ul.appendChild(li);
    li.addEventListener('click', function () {
        const name = localStorage.getItem('name'+this.id);
        const content = localStorage.getItem('content'+this.id);
        document.getElementById('nameInput').value = name;
        document.getElementById('contentInput').value = content;
        isEdit = true;
        editId = this.id;
    })
}
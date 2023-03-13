// Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
    'use strict';

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation');

    // Loop over them and prevent submission
    let isInvalid;
    Array.prototype.slice.call(forms).forEach((form) => {
        form.addEventListener('submit', (event) => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });
})();

let nameInput = document.getElementById("nameInput");
let widthInput = document.getElementById("widthInput");
let heightInput = document.getElementById("heightInput");
let depthInput = document.getElementById("depthInput");


function submitForm() {

    const validName = nameInput.checkValidity();
    const validWidth = widthInput.checkValidity();
    const validHeight = heightInput.checkValidity();
    const validDepth = depthInput.checkValidity();

    if (validName && validWidth && validHeight && validDepth) {
        const name = nameInput.value;
        const width = widthInput.value;
        const height = heightInput.value;
        const depth = depthInput.value;
        let volume = parseInt((width * height * depth / 1000000).toFixed(2));
        const table = document.getElementById("table");
        let row = table.insertRow(1);
        let cell1 = row.insertCell();
        let cell2 = row.insertCell();
        let cell3 = row.insertCell();
        let cell4 = row.insertCell();
        let cell5 = row.insertCell();
        cell1.innerText = name;
        cell2.innerText = width;
        cell3.innerText = height;
        cell4.innerText = depth;
        cell5.innerText = volume;

        let footer = document.getElementById("volumeFooter");
        let footerValue = parseInt(footer.innerText);
        console.log(footerValue);
        footer.innerText = footerValue + volume;
        document.getElementById('form');
    }

}

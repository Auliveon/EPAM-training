document.addEventListener("DOMContentLoaded", function () {


});

let knightPid = null;

function addEvents() {


}

function getWeaponTable() {
    let table = document.querySelector('#table');
    let weaponTab = document.querySelector('#tab1').querySelector('a')
    let armorTab = document.querySelector('#tab2').querySelector('a')
    armorTab.classList.remove('active');
    weaponTab.classList.add('active');
    refreshSelectedTable();

}

function getArmorTable() {
    let table = document.querySelector('#table');
    let weaponTab = document.querySelector('#tab1').querySelector('a');
    let armorTab = document.querySelector('#tab2').querySelector('a');
    weaponTab.classList.remove('active');
    armorTab.classList.add('active');
    refreshSelectedTable();
}

function getOwnedEquipment(event) {

    let inventoryTable = document.querySelector('#inventory_table');

    event.preventDefault();

    let request = new Request(event.target.getAttribute('href'));
    knightPid = event.target.getAttribute('variable');
    fetch(request)
        .then(response => response.text())
        .then(response => {
            inventoryTable.innerHTML = response;
        })
        .then(refreshSelectedTable);
}

function removeOwner(event) {

    let inventoryTable = document.querySelector('#inventory_table');

    event.preventDefault();


    fetch(event.target.getAttribute('href') + `&knightPid=${knightPid !== null ? knightPid : -1}`)
        .then(response => response.text())
        .then(response => {
            inventoryTable.innerHTML = response;
        }).then(refreshSelectedTable);
}

function refreshSelectedTable() {
    let table = document.querySelector('#table');
    let weaponTab = document.querySelector('#tab1').querySelector('a');
    let armorTab = document.querySelector('#tab2').querySelector('a');
    let isCheckedByWeight = document.querySelector('#sortCheckbox').checked;
    let isCheckedSortCheckboxByCost = document.querySelector('#sortCheckboxByCost').checked;
    let minPrice = document.querySelector('#sortCheckboxByCostMin').value;
    let maxPrice = document.querySelector('#sortCheckboxByCostMax').value;
    minPrice = minPrice === "" ? 0 : minPrice;
    maxPrice = maxPrice === "" ? 0 : maxPrice;

    if (weaponTab.classList.contains('active')) {
        fetch(`/getweapontable/?sortbyweight=${isCheckedByWeight}&sortbycost=${isCheckedSortCheckboxByCost}
        &minprice=${minPrice}&maxprice=${maxPrice}&knightid=${knightPid !== null ? knightPid : -1}`, {
            method: 'POST'
        })
            .then(response => response.text())
            .then(response => {
                table.innerHTML = response;
            })
            .then(() => {
                    if (knightPid !== null) {
                        table.querySelectorAll('button').forEach(elem => {

                            elem.disabled = false;
                        })
                    }
                }
            )

    } else if (armorTab.classList.contains('active')) {
        fetch(`/getarmortable/?sortbyweight=${isCheckedByWeight}&sortbycost=${isCheckedSortCheckboxByCost}
        &minprice=${minPrice}&maxprice=${maxPrice}&knightid=${knightPid !== null ? knightPid : -1}`, {
            method: 'POST'
        })
            .then(response => response.text())
            .then(response => {
                table.innerHTML = response;
            })
            .then(() => {
                    if (knightPid !== null) {
                        table.querySelectorAll('button').forEach(elem => {
                            elem.disabled = false;
                        })
                    }
                }
            )
    }
    refreshKnightTable();
}

function refreshKnightTable() {
    let knightTable = document.querySelector('#knight_table');

    fetch(`/getknighttable`, {
        method: 'POST'
    }).then(response => response.text())
        .then(response => {
            knightTable.innerHTML = response;
        })
}

function addWeaponOwner(event) {

    event.preventDefault();

    fetch(event.target.getAttribute('href') + `&knightpid=${knightPid}`)
        .then(response => response.text())
        .then(response => {
            refreshSelectedTable();
            let inventoryTable = document.querySelector('#inventory_table');
            inventoryTable.innerHTML = response;
        });
}

function addArmorOwner(event) {

    event.preventDefault();

    fetch(event.target.getAttribute('href') + `&knightpid=${knightPid}`)
        .then(response => response.text())
        .then(response => {
            refreshSelectedTable();
            let inventoryTable = document.querySelector('#inventory_table');
            inventoryTable.innerHTML = response;
        });
}




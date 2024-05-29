let table1 = null
let table2 = null

const tableContent = document.getElementById("table-content")
function createFirstRow(table){
    let tr = document.createElement("tr")

    for (let index = 1; index < 9; index++) {
        let td = document.createElement("td")
        td.innerHTML=index
        td.style="width:100px"
        tr.appendChild(td)
    }
    table.appendChild(tr)
}
function generateRow(lesson, table, tr){
    let td = document.createElement("td")
    if (lesson != null) {
        td.innerHTML = lesson.group.name + "<br>" + lesson.subject.teacher.fullName + "<br>" + lesson.subject.name  + "<br>"+  lesson.subject.type
        td.classList.add("slot-full")
    }
    else{
        td.classList.add("slot-empty")
    }
    td.style = "text-align:center; text-wrap:pretty; width:100px; height: 50px; word-break: break-word"

    tr.appendChild(td)
    table.appendChild(tr)
}
function generateRowsByData(data){
    let week1Days = data[0].days
    let week2Days = data[1].days
    week1Days.forEach(day => {
        let tr = document.createElement("tr")
        day.lessons.forEach(lesson=>{
            generateRow(lesson, table1, tr)
        })
    });
    week2Days.forEach(day => {
        let tr = document.createElement("tr")
        day.lessons.forEach(lesson=>{
            generateRow(lesson, table2, tr)
        })
    });
}
function clear(){
    var tables = document.getElementById("table-content");
    while (tables.firstChild) {
        tables.removeChild(tables.lastChild);
    }
}
function generateTable(e){
    e.preventDefault()
    clear()

    table1 = document.createElement("table")
    table1.setAttribute("border", "1")
    table1.classList.add("table")
    table2 = document.createElement("table")
    table2.setAttribute("border", "1")
    table2.classList.add("table")
    createFirstRow(table1)
    createFirstRow(table2)

    const formData = new FormData(e.target);

    fetch("https://vm.nathoro.ru/timetable/by-room/"+encodeURIComponent(formData.get("room")))
    .then((response) => {
        return response.json()
    })
    .then((data) => {
        generateRowsByData(data)
        tableContent.append(table1)
        tableContent.append(table2)
    })
}

document.getElementById("form-rooms").addEventListener("submit",(e)=> generateTable(e))
document.getElementById("btn-clear").addEventListener("click",()=> clear())
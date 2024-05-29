document.getElementById("form").addEventListener("submit",(e)=> {
    e.preventDefault()
    let formData = new FormData(e.target)
    if(formData.get("login").length<6){
        Swal.fire({
            title: 'Ошибка',
            text: 'Длина логина меньше 6',
            icon: 'error',
            confirmButtonText: 'Пон'
          })
    }
    else{
        Swal.fire({
            title: 'Все круто',
            icon: 'succes',
            confirmButtonText: 'Пон'
          })
    }
})

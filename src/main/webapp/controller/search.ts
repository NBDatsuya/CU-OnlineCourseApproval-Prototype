function searchCourse(field, value) {
    if (field === "" || value === "") return

    window.open(
        `searchCourse.do?field=${field}&value=${value}`,
        "newwindow")
}
function searchEnroll(field, value) {
    if (field === "" || value === "") return

    window.open(`searchEnroll.do?field=${field}&value=${value}`,'newwindow')
}
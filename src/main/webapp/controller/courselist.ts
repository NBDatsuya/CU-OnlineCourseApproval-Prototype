function enroll(status, courseID) {
    const confirm = window.confirm(status === 0 ? "确定退掉这门课吗？" :
        "确定要选这门课吗？")
    if (!confirm) return;
    if (status) {
        window.location.href = `enroll.do?course_id=${courseID}`
    } else {
        window.location.href = `unEnroll.do?course_id=${courseID}`
    }
}
function add() {
    window.location.href = `course.jsp?flag=add`
}
function edit(courseID) {
    window.location.href = `course.jsp?flag=edit&course_id=${courseID}`
}

function del(courseID) {
    const confirm = window.confirm("确定删除吗?")
    if (!confirm) return;
    window.location.href = `delCourse.do?course_id=${courseID}`
}
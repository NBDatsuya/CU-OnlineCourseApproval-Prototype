function examine(result, enrollID) {
    const confirm = window.confirm(result == 0 ? "确定不通过申请吗？" : "确定通过申请吗？")
    if (!confirm) return;
    window.location.href = `examine.do?enroll_id=${enrollID}&result=${result}`
}
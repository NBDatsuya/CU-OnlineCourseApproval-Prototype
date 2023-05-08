const cssShow = "display: block;";
const cssHide = "display: none;";

function show_container(id) {
    const containers = document.getElementsByClassName("container")
    containers[id].setAttribute("style", cssShow)
    for (let i = 0; i < containers.length; i++) {
        if (i != id)
            containers[i].setAttribute("style", cssHide)
    }
}
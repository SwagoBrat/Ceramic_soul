if (localStorage.getItem("jwtToken") != null) {
    check()
}
async function check() {
    const response = await fetch("http://localhost:8080/api/check", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("jwtToken")
        }
    })
    const name = await response.text();
    if (response.ok) {
        document.getElementById("user-info").textContent = name;
    }
}
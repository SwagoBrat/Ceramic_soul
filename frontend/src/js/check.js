check()
async function check() {
    const response = await fetch("http://localhost:8080/api/check")
    const name = await response.text();
    if (response.ok) {
        document.getElementById("user-info").textContent = name;
    }
}
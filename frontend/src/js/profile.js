profile()
async function profile() {
    try {
        const response = await fetch("http://localhost:8080/api/profile", {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("jwtToken")
            }
        })
        const user = await response.json();
        document.getElementById("name").value = user.name;
        document.getElementById("email").value = user.email;
    } catch (e) {
        console.log(e);
    }
}
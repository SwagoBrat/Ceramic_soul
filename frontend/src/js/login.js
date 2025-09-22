document.getElementById("btn-login").addEventListener("click", async function() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    try {
        const response = await fetch("http://localhost:8080/api/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({email, password})
        })
        const name = await response.text();
        alert(name)
        if (response.ok) {
            document.getElementById("user-info").textContent = name;
        }
    } catch (e) {
        alert(e);
    }
})
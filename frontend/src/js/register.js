console.log(document.getElementById("btn").value);

document.getElementById("btn").addEventListener("click", async function() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    try {
        const response = await fetch("http://localhost:8080/api/reg", {
            method: "POST",
                headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({email, password})
        })
        alert(await response.text())
    } catch (e) {
        alert(e);
    }
})

document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("loginForm");
    const registerForm = document.getElementById("registerForm");

    // Função para lidar com o login
    loginForm.addEventListener("submit", function (e) {
        e.preventDefault(); // Impede o envio padrão do formulário

        const loginData = {
            email: document.getElementById("email").value,
            senha: document.getElementById("password").value
        };

        fetch("/api/usuarios/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json" // Define que o corpo da requisição é JSON
            },
            body: JSON.stringify(loginData)
        })
            .then(response => {
                if (response.ok) {
                    alert("Login realizado com sucesso!");
                    window.location.href = "/ver-receitas"; // Redireciona após sucesso
                } else if (response.status === 401) {
                    alert("Credenciais inválidas. Verifique seu e-mail e senha.");
                } else {
                    throw new Error("Erro ao fazer login.");
                }
            })
            .catch(error => {
                console.error("Erro:", error);
                alert("Erro ao fazer login. Tente novamente mais tarde.");
            });
    });

    // Função para lidar com o registro
    registerForm.addEventListener("submit", function (e) {
        e.preventDefault(); // Impede o envio padrão do formulário

        const registerData = {
            nome: document.getElementById("name").value,
            email: document.getElementById("emailRegister").value,
            senha: document.getElementById("passwordRegister").value
        };

        fetch("/api/usuarios/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json" // Define que o corpo da requisição é JSON
            },
            body: JSON.stringify(registerData)
        })
            .then(response => {
                if (response.ok) {
                    alert("Usuário registrado com sucesso!");
                    toggleForms(); // Volta para o formulário de login
                } else if (response.status === 409) {
                    alert("Usuário já existe. Tente usar outro e-mail.");
                } else {
                    throw new Error("Erro ao registrar usuário.");
                }
            })
            .catch(error => {
                console.error("Erro:", error);
                alert("Erro ao registrar. Tente novamente mais tarde.");
            });
    });
});

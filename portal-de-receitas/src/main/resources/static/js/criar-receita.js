document.addEventListener("DOMContentLoaded", function () {
    const createRecipeForm = document.getElementById("create-recipe-form");
    const categorySelect = document.getElementById("recipe-category");

    // Carregar categorias dinamicamente
    function loadCategories() {
        fetch("/api/categorias")
            .then(response => response.json())
            .then(categories => {
                categories.forEach(category => {
                    const option = document.createElement("option");
                    option.value = category.id;
                    option.textContent = category.nome;
                    categorySelect.appendChild(option);
                });
            })
            .catch(error => console.error("Erro ao carregar categorias:", error));
    }

    // Submeter o formulário para criar uma nova receita
    createRecipeForm.addEventListener("submit", function (e) {
        e.preventDefault();

        const recipeData = {
            nome: document.getElementById("recipe-name").value,
            descricao: document.getElementById("recipe-description").value,
            categoriaId: categorySelect.value,
            ingredientes: document.getElementById("recipe-ingredients").value.split(","),
            passos: document.getElementById("recipe-steps").value.split(",")
        };

        fetch("/api/receitas", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(recipeData),
        })
            .then(response => {
                if (response.ok) {
                    alert("Receita criada com sucesso!");
                    window.location.href = "/ver-receitas";
                } else {
                    throw new Error("Erro ao criar receita.");
                }
            })
            .catch(error => {
                console.error("Erro ao criar receita:", error);
                alert("Erro ao criar receita. Tente novamente mais tarde.");
            });
    });

    loadCategories();
});

document.addEventListener("DOMContentLoaded", function () {
    const searchNameInput = document.getElementById("search-name");
    const searchCategorySelect = document.getElementById("search-category");
    const searchIngredientInput = document.getElementById("search-ingredient");
    const searchButton = document.getElementById("search-button");
    const resultsContainer = document.getElementById("results-container");

    // Carregar categorias dinamicamente
    function loadCategories() {
        fetch("/api/categorias")
            .then(response => response.json())
            .then(categories => {
                categories.forEach(category => {
                    const option = document.createElement("option");
                    option.value = category.id;
                    option.textContent = category.nome;
                    searchCategorySelect.appendChild(option);
                });
            })
            .catch(error => console.error("Erro ao carregar categorias:", error));
    }

    // Buscar receitas com base nos critérios
    function searchRecipes() {
        const name = searchNameInput.value.trim();
        const categoryId = searchCategorySelect.value;
        const ingredient = searchIngredientInput.value.trim();

        let url = "/api/receitas/buscar?";
        if (name) url += `nome=${encodeURIComponent(name)}&`;
        if (categoryId) url += `categoriaId=${encodeURIComponent(categoryId)}&`;
        if (ingredient) url += `ingrediente=${encodeURIComponent(ingredient)}`;

        fetch(url)
            .then(response => response.json())
            .then(recipes => displayRecipes(recipes))
            .catch(error => {
                console.error("Erro ao buscar receitas:", error);
                resultsContainer.innerHTML = "<p>Erro ao buscar receitas. Tente novamente mais tarde.</p>";
            });
    }

    // Exibir receitas no container de resultados
    function displayRecipes(recipes) {
        if (recipes.length === 0) {
            resultsContainer.innerHTML = "<p>Nenhuma receita encontrada.</p>";
            return;
        }

        resultsContainer.innerHTML = ""; // Limpa os resultados anteriores

        recipes.forEach(recipe => {
            const recipeCard = document.createElement("div");
            recipeCard.className = "recipe-card";

            const recipeName = document.createElement("h3");
            recipeName.textContent = recipe.nome;

            const recipeDescription = document.createElement("p");
            recipeDescription.textContent = recipe.descricao;

            const viewDetailsButton = document.createElement("button");
            viewDetailsButton.textContent = "Ver Detalhes";
            viewDetailsButton.onclick = () => viewRecipeDetails(recipe.id);

            recipeCard.appendChild(recipeName);
            recipeCard.appendChild(recipeDescription);
            recipeCard.appendChild(viewDetailsButton);

            resultsContainer.appendChild(recipeCard);
        });
    }

    // Exibir detalhes de uma receita
    function viewRecipeDetails(recipeId) {
        window.location.href = `/receita/${recipeId}`;
    }

    searchButton.addEventListener("click", searchRecipes);
    loadCategories();
});

document.addEventListener("DOMContentLoaded", () => {
    const recipesContainer = document.getElementById("recipesContainer");
    const searchForm = document.getElementById("searchForm");
    const searchName = document.getElementById("searchName");
    const searchCategory = document.getElementById("searchCategory");
    const searchIngredient = document.getElementById("searchIngredient");

    /**
     * Carregar categorias no dropdown de filtro.
     */
    async function loadCategories() {
        try {
            const response = await fetch("/api/categorias");
            if (!response.ok) throw new Error("Erro ao buscar categorias");
            
            const categories = await response.json();
            categories.forEach(category => {
                const option = document.createElement("option");
                option.value = category.id;
                option.textContent = category.nome;
                searchCategory.appendChild(option);
            });
        } catch (error) {
            console.error("Erro ao carregar categorias:", error);
        }
    }

    /**
     * Carregar receitas com base nos filtros fornecidos.
     * @param {Object} filters Filtros de busca (nome, categoria, ingrediente).
     */
    async function loadRecipes(filters = {}) {
        try {
            // Construir query string para filtros
            const queryParams = new URLSearchParams(filters).toString();
            const response = await fetch(`/api/receitas?${queryParams}`);
            if (!response.ok) throw new Error("Erro ao buscar receitas");

            const receitas = await response.json();
            recipesContainer.innerHTML = ""; // Limpar resultados anteriores

            if (receitas.length === 0) {
                recipesContainer.textContent = "Nenhuma receita encontrada.";
                return;
            }

            receitas.forEach(receita => {
                const recipeCard = document.createElement("div");
                recipeCard.classList.add("recipe-card");
                recipeCard.innerHTML = `
                    <h3>${receita.nome}</h3>
                    <p>Categoria: ${receita.categoria?.nome || "Sem Categoria"}</p>
                    <p>${receita.descricao.substring(0, 100)}...</p>
                    <button onclick="viewRecipe(${receita.id})">Ver Receita</button>
                `;
                recipesContainer.appendChild(recipeCard);
            });
        } catch (error) {
            console.error("Erro ao carregar receitas:", error);
            recipesContainer.textContent = "Erro ao carregar receitas. Tente novamente mais tarde.";
        }
    }

    /**
     * Redirecionar para a página de detalhes da receita.
     * @param {Number} id ID da receita a ser visualizada.
     */
    window.viewRecipe = (id) => {
        window.location.href = `/receita/${id}`;
    };

    /**
     * Buscar receitas ao enviar o formulário.
     */
    searchForm.addEventListener("submit", (event) => {
        event.preventDefault(); // Evitar reload da página
        const filters = {
            nome: searchName.value.trim(),
            categoriaId: searchCategory.value,
            ingrediente: searchIngredient.value.trim()
        };
        loadRecipes(filters);
    });

    // Inicializar a página
    loadCategories();
    loadRecipes();
});

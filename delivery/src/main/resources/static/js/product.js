document.querySelector("form").addEventListener("submit", async function (e) {
    e.preventDefault();
  
    const productData = {
      name: this.name.value,
      valueProduct: parseFloat(valueProduct.value),
      describe: describe.value
    };
  
    try {
      const response = await fetch("/admin/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(productData)
      });
  
      if (response.ok) {
        alert("Produto cadastrado com sucesso!!");
      } else {
        alert("Ocorreu um erro, tente novamente.");
      }
    } catch (error) {
      console.error("Erro:", error);
    }
  });
  
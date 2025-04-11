document.getElementById("formLogin").addEventListener("submit", async function (e) {
    e.preventDefault();
  
    const camposInput = document.getElementsByClassName("required");
    const spans = document.getElementsByClassName("span-required");
  
    const data = {
      login: this.login.value,
      password: this.password.value,
    };
  
    loginValidate();
    passwordValidate();
  
    const hasError = Array.from(spans).some(span => span.style.display === "block");
    if (hasError) return;
  
    try {
      const response = await fetch("/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
      });
  
      if (response.ok) {
        alert("Login realizado com sucesso!");
        if (result.role === "ADMIN") {
          window.location.href = "/admin/home";
        } else if (result.role === "USER") {
          window.location.href = "/user/home";
        } else {
          window.location.href = "/";
        }
      } else {
        alert("Usuário ou senha inválidos.");
      }
  
    } catch (error) {
      console.error("Erro na requisição:", error);
    }
  
    function setError(index, message) {
      camposInput[index].style.border = "2px solid #e63636";
      spans[index].style.display = "block";
      spans[index].textContent = message;
    }
  
    function clearError(index) {
      camposInput[index].style.border = "";
      spans[index].style.display = "none";
      spans[index].textContent = "";
    }
  
    function loginValidate() {
      if (camposInput[0].value.trim().length < 3) {
        setError(0, "O nome precisa ter ao menos 3 caracteres");
      } else {
        clearError(0);
      }
    }
  
    function passwordValidate() {
      if (camposInput[1].value.trim().length < 6) {
        setError(1, "A senha deve ter no mínimo 6 caracteres");
      } else {
        clearError(1);
      }
    }
  });
  
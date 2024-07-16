Sistema de Conversão de Moedas
Este é um simples sistema de conversão de moedas que utiliza a API ExchangerateApi para obter as taxas de câmbio atuais e converter valores entre diferentes moedas.

Funcionalidades
Conversão de Dólar (USD) para Real (BRL)
Conversão de Real (BRL) para Dólar (USD)
Conversão de Dólar (USD) para Euro (EUR)
Conversão de Euro (EUR) para Dólar (USD)
Conversão de Dólar (USD) para Libra Esterlina (GBP)
Conversão de Libra Esterlina (GBP) para Dólar (USD)
Pré-requisitos
Java Development Kit (JDK) 8 ou superior instalado.
Conexão com a Internet para acessar a API de taxa de câmbio.
Como usar
Clone este repositório para sua máquina local:

sh
Copiar código
git clone https://github.com/GustavSilvDev/Conversor-de-Moedas.git
Navegue até o diretório do projeto:

sh
Copiar código
cd Conversor-de-Moedas
Compile o código:

sh
Copiar código
javac Principal.java
Execute o programa:

sh
Copiar código
java Principal
Exemplo de Uso
Ao executar o programa, você verá um menu com as opções de conversão. Selecione a opção desejada digitando o número correspondente e pressione Enter. Em seguida, insira o valor a ser convertido e pressione Enter novamente. O programa exibirá o valor convertido.

Tratamento de Exceções
O programa trata as seguintes exceções:

InputMismatchException: Ocorre quando o usuário insere um valor inválido. O programa exibirá uma mensagem de erro e solicitará a entrada novamente.
 

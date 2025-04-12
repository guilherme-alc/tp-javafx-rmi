# Sistema de E-mails com Java RMI e JavaFX

## Descrição do Projeto

Este projeto é um sistema de e-mails utilizando **Java RMI** (Remote Method Invocation) para comunicação entre clientes e servidor, e **JavaFX** para a interface gráfica. O servidor mantém uma base de dados de usuários (e-mails) e suas mensagens, enquanto os clientes podem cadastrar seus e-mails, enviar mensagens para outros usuários e receber notificações quando novos e-mails forem recebidos.

## Requisitos do Sistema

1. O **servidor** deve manter uma base de dados de usuários (e-mails) e suas mensagens.
2. Os **clientes** devem ser capazes de:
   - Cadastrar seu e-mail no servidor.
   - Enviar mensagens para outros usuários cadastrados.
   - Receber notificações quando novos e-mails forem recebidos.
3. O **armazenamento de dados** deve ser feito em arquivos (JSON, XML, arquivos de texto, etc.).
4. O sistema deve utilizar **Java RMI** para comunicação entre cliente e servidor.
5. O **servidor** deve ser capaz de manter o estado das mensagens e e-mails de maneira persistente.
6. **Interface Gráfica**:
   - O sistema possui uma interface gráfica para os clientes, implementada com **JavaFX**.
7. O sistema deve oferecer **feedback ao cliente**, informando se o e-mail foi enviado corretamente ou se houve algum problema (exemplo: destinatário não existente).

## Como Executar o Projeto

### Configuração do Projeto no Eclipse

#### Usando o SDK do JavaFX dentro do Projeto

1. No Eclipse, clique com o botão direito no projeto **cliente** (projeto com JavaFX) → **Build Path** → **Configure Build Path**.
2. Na aba **Libraries** → **Classpath** → **Add JARs**.
3. Navegue até `cliente/lib/javafx-sdk-21.0.6/lib` e selecione **todos os arquivos JAR**, exceto o arquivo `javafx.properties`.
4. Clique com o botão direito no projeto **cliente** → **Run As** → **Run Configurations...** → **Java Application**.
5. Na aba **Arguments**, insira em **VM Arguments**:
   ```bash
   --module-path lib/javafx-sdk-21.0.6/lib --add-modules javafx.controls,javafx.fxml

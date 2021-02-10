## Instalando o GnuTLS no Ubunbu

Execute o comando no terminal:

```
$ sudo apt install gnutls-bin
```
```
$ brew install gnutls
```

### **Links úteis**

[GnuTLS](https://gnutls.org/)

[Documentação da REST API do GitHub](https://developer.github.com/v3/)


### **Comandos executados em aula**

```
$ telnet www.uol.com.br 80

GET / HTTP/1.1
Host: www.uol.com.br
Accept: text/html
```

```
$ telnet www.uol.com.br 443

GET / HTTP/1.1
Host: www.uol.com.br
Accept: text/html
```

```
$ gnutls-cli www.uol.com.br

GET / HTTP/1.1
Host: www.uol.com.br
Accept: text/html
```

```
$ gnutls-cli www.uol.com.br

GET /users/danilogmoura/repos
Host: api.github.com
Accept: application/json
```
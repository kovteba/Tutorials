## RabbitMQ

- [How install rabbitMQ](#How-install-rabbitMQ)
- [Start server](#Start-server)
- [Show active plugins](#Show-active-plugins)
- [Activate rabbitmq_management](#Activate-rabbitmq_management)
- [WEB UI](#WEB-UI)
- [Enable rabbitmq_stomp](#Enable-rabbitmq_stomp)
- [rabbitmqctl help](#rabbitmqctl-help)
- [](#)


---

## How install rabbitMQ
- Add a repository
```
sudo echo 'deb http://www.rabbitmq.com/debian testing main' | sudo tee /etc/apt/sources.list.d/rabbitmq.list
```
- Install server
```
wget -O- https://www.rabbitmq.com/rabbitmq-release-signing-key.asc | sudo apt-key add -
sudo apt-get update
sudo apt-get install rabbitmq-server
```
- При включенном брандмауэре UFW, тобы получить доступ к консоли удаленного управления RabbitMQ, вам необходимо разрешить входящий TCP-трафик на портах 4369, 5671, 5672, 25672, 15672, 15675, 61613, 61614, 1883, 15674.
```
sudo ufw allow 4369
sudo ufw allow 5671:5672
sudo ufw allow 15672:15675
sudo ufw allow 25672
sudo ufw allow 61613:61614
sudo ufw allow 1883
sudo ufw allow 15674
```

---

## Start server
```
sudo systemctl start rabbitmq-server
sudo rabbitmqctl start_app
```

---

## Show active plugins
```
sudo rabbitmq-plugins list
```

---

## Activate rabbitmq_management
```
sudo rabbitmq-plugins enable rabbitmq_management
```

---

## WEB UI
```
localhost:15672
```
```
sudo rabbitmqctl list_permissions
sudo rabbitmqctl guest cinder guest
sudo rabbitmqctl status
```

--- 

## Enable rabbitmq_stomp
```
sudo rabbitmq-plugins enable rabbitmq_stomp
```

- Add to file (etc/rabbitmq/rabbitmq-env.conf)
```
# Defaults to 61613.
#NODE_PORT=61613

#stomp.listeners.tcp.1 = 127.0.0.1:61613
#stomp.listeners.tcp.2 = ::1:61613

#stomp.tcp_listen_options.backlog = 4096
#stomp.tcp_listen_options.recbuf  = 131072
#stomp.tcp_listen_options.sndbuf  = 131072

#stomp.tcp_listen_options.keepalive = true
#stomp.tcp_listen_options.nodelay   = true 

#stomp.tcp_listen_options.exit_on_close = true
#stomp.tcp_listen_options.send_timeout  = 120
```

---

## rabbitmqctl help
```
sudo rabbitmqctl help
```
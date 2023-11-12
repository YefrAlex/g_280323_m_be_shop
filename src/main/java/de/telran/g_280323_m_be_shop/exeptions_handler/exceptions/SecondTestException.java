package de.telran.g_280323_m_be_shop.exeptions_handler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
// второй способ обработки ошибок
// плюс в том что это глобальный обработчик ошибок, обрабатывает ошибки в любом классе
// + очень простой способ
// минус в том что не отправляется информативное сообщение о причине возникновения ошибок
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SecondTestException extends RuntimeException{
    public SecondTestException(String message) {
        super(message);
    }
}

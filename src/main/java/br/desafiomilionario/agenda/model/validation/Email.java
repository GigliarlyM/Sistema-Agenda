package br.desafiomilionario.agenda.model.validation;

import br.desafiomilionario.agenda.exception.BusinessException;

import javax.naming.directory.NoSuchAttributeException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email extends Validate<String> {

    public Email(String email) {
        this.value(email);
    }

    @Override
    void validated(String newValue) {
        // "a@b.co" eh um email valido para esse regex
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(newValue);

        if (!matcher.matches()) {
            throw new BusinessException("Email inválido!!");
        }
    }
}

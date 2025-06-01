package br.desafiomilionario.agenda.model.validation;

import br.desafiomilionario.agenda.exception.BusinessException;

import javax.naming.directory.NoSuchAttributeException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telefone extends Validate<String> {
    public Telefone(String telefone) {
        this.value(telefone);
    }

    @Override
    void validated(String newValue) {
        /* Validos:
            88 8888-8888
            8888888888
            88 98888-8888 (so eh valido pelo o '9')
            88988888888 (so eh valido pelo o '9')
        * */
        String regexAprimoradaBrasil = "^\\(?([1-9]{2})\\)?\\s?(?:9[1-9]|\\d)[1-9]\\d{3}\\-?\\d{4}$";

        Pattern pattern = Pattern.compile(regexAprimoradaBrasil);
        Matcher matcher = pattern.matcher(newValue);

        if (!matcher.matches()) {
            throw new BusinessException("Telefone inválido!!");
        }
    }
}

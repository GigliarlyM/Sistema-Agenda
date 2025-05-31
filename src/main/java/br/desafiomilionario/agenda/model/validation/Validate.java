package br.desafiomilionario.agenda.model.validation;

import javax.naming.directory.NoSuchAttributeException;

class Validate<E> {
    private E value;

    public E value() {
        return value;
    }
    public void value(E newValue) throws NoSuchAttributeException {
        this.validated(newValue);
        this.value = newValue;
    }

    void validated(E newValue) throws NoSuchAttributeException {}
}

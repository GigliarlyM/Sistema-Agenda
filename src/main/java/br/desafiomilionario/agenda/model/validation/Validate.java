package br.desafiomilionario.agenda.model.validation;

class Validate<E> {
    private E value;

    public E value() {
        return value;
    }
    public void value(E newValue) throws Exception {
        this.validated(newValue);
        this.value = newValue;
    }

    void validated(E newValue) throws Exception {}
}

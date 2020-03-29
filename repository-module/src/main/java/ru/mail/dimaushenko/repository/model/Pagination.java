package ru.mail.dimaushenko.repository.model;

public class Pagination {

    private Integer startElement;
    private Integer elementsPerPage;

    public Integer getStartElement() {
        return startElement;
    }

    public void setStartElement(Integer startElement) {
        this.startElement = startElement;
    }

    public Integer getElementsPerPage() {
        return elementsPerPage;
    }

    public void setElementsPerPage(Integer elementsPerPage) {
        this.elementsPerPage = elementsPerPage;
    }

}

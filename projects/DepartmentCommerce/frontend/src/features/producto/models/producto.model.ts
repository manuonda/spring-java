export interface Producto {
    private Long id;

    private String name;
    private String description;
    private double price;
    private int qty;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;
}
package service.ricotunes.giftcards.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transaction_image")
public class TransactionImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "transaction",
            joinColumns = @JoinColumn(name = "transaction_image_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "transactions", referencedColumnName = "id"))
    private Transactions transaction;

    @Column(name = "image", length = 4096, columnDefinition = "TEXT")
    private String image;
}

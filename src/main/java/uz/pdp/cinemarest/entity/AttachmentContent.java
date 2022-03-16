package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class AttachmentContent  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

   @OneToOne(cascade = CascadeType.ALL)
    Attachment attachment;

     byte[] data;

    public AttachmentContent(Attachment attachment, byte[] data) {
        this.attachment = attachment;
        this.data = data;
    }
}

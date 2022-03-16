package uz.pdp.cinemarest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import uz.pdp.cinemarest.entity.template.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "attachments")
@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class Attachment extends AbsEntity {
    String contentType;
    Long size;

}

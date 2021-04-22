package de.codecentric.psd.worblehat.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.ISBNValidator;

public class ISBNConstraintValidator implements ConstraintValidator<ISBN, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    // Don't validate null, empty and blank strings, since these are validated by @NotNull,
    // @NotEmpty and @NotBlank
    if (StringUtils.isNotBlank(value)) {
      String isbn = StringUtils.getDigits(value);
      if (StringUtils.length(isbn) <= 10) {
        return ISBNValidator.getInstance().isValidISBN10(isbn);
      }
      if (StringUtils.length(isbn) >= 13) {
        return ISBNValidator.getInstance().isValidISBN13(isbn);
      }
    }
    return true;
  }
}

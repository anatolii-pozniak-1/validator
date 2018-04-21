# validator
Simple validation structure. Project contains base validation class *Validation* and implementation of String validation.
String validation consist of the next implementations:
* __Validation<V, E>__ - Base validation interface, __V__ - value, __E__ - error
* __StringEmptyValidation\<E>__ - check value empty or not
* __StringComplexValidation\<E>__ - list of validations
* __StringEqualsValidation\<E>__ - chek actual value with expected __*(args: actuall, expected, error)*__
* __StringPatternValidation\<E>__ - chek validation by pattern __*(args: value, error, pattern)*__
* __StringLengthValidation\<E>__  - check length validation __*( args: value, error, minLength, maxLength)*__
##### Resources
* __StringValidationFabric<E>__ - base vlidation fabric for the next values: 
  * *EMAIL, PASSWORD, EMPTY, OPTIONAL, LENGTH, USER_NAME, TELEPHONE, REFERRAL, USER_AGE, NAME, CITY, STATE, CONFIRM_PASSWORD*
* __StringErrorResource<E>__  - provide errors values for the validation types above

# Base Interface - *Validation*

```java

/**
 * 
 * @param <V> - Type of validation value
 * @param <E> - Type of error value
 */
public interface Validation<V, E> {
    /**
     * Check value valid or not
     * @return is valid
     */
    boolean isValid();

    /**
     * initialize validator value
     * @param actual - value to check
     */
    void setValue(V actual);

    /**
     *
     * @return E - error value
     */
    E getError();
    /**
     *
     * @return V - validation value
     */
    V getValue();
}


```

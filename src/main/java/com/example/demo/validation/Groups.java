package com.example.demo.validation;

import jakarta.validation.groups.Default;

/**
 * A utility interface for defining validation groups.
 */
public interface Groups {

    /**
     * Validation group for create operations.
     */
    interface Create extends Default {}

    /**
     * Validation group for update operations.
     */
    interface Delete extends Default {}
    
    /**
     * Validation group for update operations.
     */
    interface Update extends Default {}
    
    /**
     * Validation group for update operations.
     */
    interface Query extends Default {}
}

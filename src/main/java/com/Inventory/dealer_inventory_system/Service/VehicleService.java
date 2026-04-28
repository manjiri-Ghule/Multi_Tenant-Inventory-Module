package com.Inventory.dealer_inventory_system.Service;

import com.Inventory.dealer_inventory_system.Entity.Dealer;
import com.Inventory.dealer_inventory_system.Entity.Vehicle;
import com.Inventory.dealer_inventory_system.Entity.VehicleStatus;
import com.Inventory.dealer_inventory_system.Entity.SubscriptionType;
import com.Inventory.dealer_inventory_system.Repository.DealerRepository;
import com.Inventory.dealer_inventory_system.Repository.VehicleRepository;
import com.Inventory.dealer_inventory_system.config.Tenant.TenantContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DealerRepository dealerRepository;

    // 🔥 FIXED CREATE
    public Vehicle createVehicle(Vehicle vehicle, UUID dealerId) {

        Optional<Dealer> optionalDealer = dealerRepository
                .findByIdAndTenantId(dealerId, TenantContext.getTenantId());

        if (!optionalDealer.isPresent()) {
            throw new RuntimeException("Dealer not found");
        }

        Dealer dealer = optionalDealer.get();

        vehicle.setDealer(dealer);
        vehicle.setTenantId(TenantContext.getTenantId());

        return vehicleRepository.save(vehicle);
    }

    // 🔥 FIXED GET
    public Vehicle getVehicleById(UUID id) {

        Optional<Vehicle> optionalVehicle = vehicleRepository
                .findByIdAndTenantId(id, TenantContext.getTenantId());

        if (!optionalVehicle.isPresent()) {
            throw new RuntimeException("Vehicle not found");
        }

        return optionalVehicle.get();
    }

    public Page<Vehicle> filterVehicles(
            String model,
            VehicleStatus status,
            BigDecimal min,
            BigDecimal max,
            Pageable pageable) {

        return vehicleRepository.filter(
                TenantContext.getTenantId(),
                model,
                status,
                min,
                max,
                pageable
        );
    }

    // 🔥 FIXED PREMIUM
    public List<Vehicle> getPremiumVehicles() {
        return vehicleRepository.premiumVehicles(
                TenantContext.getTenantId(),
                SubscriptionType.PREMIUM
        );
    }

    public Vehicle updateVehicle(UUID id, Vehicle input) {

        Vehicle vehicle = getVehicleById(id);

        if (input.getModel() != null) vehicle.setModel(input.getModel());
        if (input.getPrice() != null) vehicle.setPrice(input.getPrice());
        if (input.getStatus() != null) vehicle.setStatus(input.getStatus());

        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(UUID id) {
        vehicleRepository.delete(getVehicleById(id));
    }
}
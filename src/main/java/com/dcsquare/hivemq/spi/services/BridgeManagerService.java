package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.bridge.Bridge;
import com.dcsquare.hivemq.spi.bridge.State;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Collection;
import java.util.List;

/**
 * The Bridge Manager Service which can be used to
 * add, start, stop, remove and list bridge information at runtime
 * <p/>
 * While typically HiveMQ bridges are configured in a <code>bridge.xml</code> file,
 * it is entirely possible to write an own bridge configuration mechanism with that
 * manager in your plugin.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public interface BridgeManagerService {

    /**
     * Adds a new {@link Bridge} to the bridge manager. The bridge won't be started
     * but can be listed.
     *
     * @param bridge the bridge to add to the bridge manager.
     */
    public void addBridge(final Bridge bridge);

    /**
     * Removes a bridge from the bridge manager. If the bridge to remove
     * is running, it will be stopped and terminated before it is removed.
     * Please note, that this method will block until the bridge is terminated.
     * If you want to stop the bridge asynchronously, consider using the <code>stopBridge</code>
     * method before removing the bridge.
     * <p/>
     * If the passed bridge to remove doesn't exist, nothing will happen.
     *
     * @param bridge the bridge to stop (if running) and remove
     */
    public void removeBridge(final Bridge bridge);

    /**
     * Starts a bridge. If the bridge wasn't added to the manager before,
     * it will be added automatically.
     * <p/>
     * This method starts the bridge asynchronously. You can use the returned
     * {@link ListenableFuture} to decide if you want to block until the bridge is started
     * or if you want to use a reactive programming pattern.
     * <p/>
     * Note, that you can start a LAZY bridge manually with this method without waiting for
     * passing the configured threshold. Also, you can reconnect disconnected bridges with
     * bridge mode ONCE.
     *
     * @param bridge the bridge to start
     * @return a {@link ListenableFuture} which you can use to block or use to react
     *         when the bridge started
     */
    public ListenableFuture<Void> startBridge(final Bridge bridge);

    /**
     * Stops a bridge. This method does not remove a bridge from the bridge manager.
     * If you would like to get the bridge also removed from the bridge manager, consider
     * using the <code>removeBridge</code> method.
     * <p/>
     * This method stops the bridge asynchronously. You can use the returned
     * {@link ListenableFuture} to decide if you want to block until the bridge is stopped
     * or if you want to use a reactive programming pattern.
     *
     * @param bridge the bridge to stop
     * @return a {@link ListenableFuture} which you can use to block or use to react
     *         when the bridge stopped
     */
    public ListenableFuture<Void> stopBridge(final Bridge bridge);

    /**
     * Returns a {@link Collection} of all bridges registered to this bridge manager.
     *
     * @return a {@link Collection} of all bridges registered to this bridge manager
     */
    public Collection<Bridge> listBridges();

    /**
     * Stops all bridges. This method does not remove bridges from the bridge manager.
     * <p/>
     * This method stops the bridges asynchronously. You can use the returned
     * {@link ListenableFuture} to decide if you want to block until all bridges are stopped
     * or if you want to use a reactive programming pattern.
     *
     * @return a {@link ListenableFuture} which you can use to block or use to react
     *         when all bridges are stopped
     */
    public ListenableFuture<List<Void>> stopAllBridges();

    /**
     * Returns the state of a given bridge. If the passed bridge does not exist,
     * an Optional with an absent value will be returned
     *
     * @param bridge the bridge to check the state for.
     * @return a {@link Optional} with the state for a bridge.
     */
    public Optional<State> getBridgeState(final Bridge bridge);
}
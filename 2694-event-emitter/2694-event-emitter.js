class EventEmitter {
  constructor() {
    this.events = new Map();
  }

  /**
   * @param {string} eventName
   * @param {Function} callback
   * @return {Object}
   */
  subscribe(eventName, callback) {
    if (!this.events.has(eventName)) {
      this.events.set(eventName, []);
    }

    const listeners = this.events.get(eventName);
    const listener = { callback };
    listeners.push(listener);

    return {
      unsubscribe: () => {
        const arr = this.events.get(eventName);
        if (!arr) return;
        const idx = arr.indexOf(listener);
        if (idx !== -1) arr.splice(idx, 1);
      }
    };
  }

  /**
   * @param {string} eventName
   * @param {Array} args
   * @return {Array}
   */
  emit(eventName, args = []) {
    const listeners = this.events.get(eventName);
    if (!listeners) return [];
    return listeners.map((l) => l.callback(...args));
  }
}

/**
 * const emitter = new EventEmitter();
 *
 * const sub = emitter.subscribe('firstEvent', (...args) => args.join(','));
 * emitter.emit('firstEvent', [1, 2, 3]); // ["1,2,3"]
 * sub.unsubscribe();
 * emitter.emit('firstEvent', [4, 5, 6]); // []
 */